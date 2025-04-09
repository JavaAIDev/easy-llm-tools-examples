package com.javaaidev.easyllmtools.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaaidev.easyllmtools.integration.mcp.McpToolHelper;
import com.javaaidev.easyllmtools.tools.nytimes.api.StoriesApiLLMToolkit.LLMToolFactory_sectionFormatGet;
import com.javaaidev.easyllmtools.tools.nytimes.api.StoriesApiLLMToolkit.LLMTool_sectionFormatGet;
import com.javaaidev.easyllmtools.tools.nytimes.api.StoriesApiToolConfiguration;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvBuilder;
import io.modelcontextprotocol.server.McpServer;
import io.modelcontextprotocol.server.transport.HttpServletSseServerTransportProvider;
import io.modelcontextprotocol.spec.McpSchema.ServerCapabilities;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.server.Server;

public class McpServerMain {

  private final Dotenv dotenv = new DotenvBuilder().ignoreIfMalformed().ignoreIfMissing().load();
  private final McpToolHelper mcpToolHelper = new McpToolHelper();

  public void start() throws Exception {
    var transportProvider = new HttpServletSseServerTransportProvider(
        new ObjectMapper(), "/messages", "/sse");
    McpServer.async(
            transportProvider)
        .serverInfo("easy-llm-tools-example", "1.0.0")
        .capabilities(ServerCapabilities.builder()
            .logging()
            .tools(true)
            .build())
        .tools(
            mcpToolHelper.toAsyncTool(topStoriesTool())
        )
        .build();
    var server = new Server(9000);
    var context = new ServletContextHandler();
    context.setContextPath("/");
    context.addServlet(transportProvider, "/*");
    server.setHandler(context);
    server.start();
    server.join();
  }

  private LLMTool_sectionFormatGet topStoriesTool() {
    var config = new StoriesApiToolConfiguration();
    var apiKey = dotenv.get("NYTIMES_API_KEY");
    config.setApiKey(apiKey);
    return new LLMToolFactory_sectionFormatGet().create(config);
  }

  public static void main(String[] args) throws Exception {
    new McpServerMain().start();
  }
}

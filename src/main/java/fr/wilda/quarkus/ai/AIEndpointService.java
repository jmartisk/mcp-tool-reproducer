package fr.wilda.quarkus.ai;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.ToolBox;
import io.quarkiverse.langchain4j.mcp.runtime.McpToolBox;
import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.ApplicationScoped;

@RegisterAiService
@ApplicationScoped
public interface AIEndpointService {
  // Add some instructions to my LLM
  @UserMessage("La question posée est la suivante : {question}")
  @McpToolBox
  String askAQuestion(String question);

  // Add some instructions to my LLM
  @UserMessage("La question posée est la suivante : {question}")
  //uncomment to fix the exception
  //@ToolBox(TimeAndDateTool.class)
  @McpToolBox
  Multi<String> askAQuestionStream(String question);
}

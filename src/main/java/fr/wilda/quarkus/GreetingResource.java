package fr.wilda.quarkus;

import fr.wilda.quarkus.ai.AIEndpointService;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.concurrent.TimeUnit;

@Path("ai")
public class GreetingResource {


  @Inject
  AIEndpointService aiEndpointService;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/ask-question")
  public String askQuestion() {
    return aiEndpointService.askAQuestion("Give me the stars number of the quarkus github repository");
  }


  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/ask-question-stream")
  public void askQuestionStream() {
    aiEndpointService.askAQuestionStream("Give me the stars number of the quarkus github repository")
        .subscribe()
        .asStream()
        .forEach(token -> {
          try {
            TimeUnit.MILLISECONDS.sleep(150);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          Log.info(token);
        });
  }

}

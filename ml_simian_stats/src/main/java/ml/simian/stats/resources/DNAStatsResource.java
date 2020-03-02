package ml.simian.stats.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import ml.simian.stats.core.dnaStats.DNAStatsService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/stats")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DNAStatsResource {

    private final DNAStatsService dnaStatsService;

    @Inject
    public DNAStatsResource(DNAStatsService dnaStatsService) {
        this.dnaStatsService = dnaStatsService;
    }

    @GET
    @Timed
    public Response stats() {
        return Response.ok(dnaStatsService.dnaAnalysisStats()).build();
    }

}

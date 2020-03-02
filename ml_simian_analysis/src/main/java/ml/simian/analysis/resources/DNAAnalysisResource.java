package ml.simian.analysis.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import ml.simian.analysis.command.DNASequence;
import ml.simian.analysis.core.dnaAnalysis.DNAAnalysisService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.FORBIDDEN;

@Path("/simian")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DNAAnalysisResource {

    private final DNAAnalysisService DNAAnalysisService;

    @Inject
    public DNAAnalysisResource(DNAAnalysisService DNAAnalysisService) {
        this.DNAAnalysisService = DNAAnalysisService;
    }

    @POST
    @Timed
    public Response analyzeDNA(DNASequence sequence) {
        if (DNAAnalysisService.analiseSequence(sequence).isSimian()){
            return Response.ok().build();
        }

        return Response.status(FORBIDDEN).build();
    }

}

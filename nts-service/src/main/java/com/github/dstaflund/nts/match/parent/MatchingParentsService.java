package com.github.dstaflund.nts.match.parent;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/matching/parents")
@Produces(MediaType.APPLICATION_JSON)
public class MatchingParentsService {

    @GET
    public List<String> getMatchingParents(@Valid @BeanParam MatchingParentsParams ctx){
        return MatchingParentsProvider.findMatchingSnippets(ctx);
    }
}

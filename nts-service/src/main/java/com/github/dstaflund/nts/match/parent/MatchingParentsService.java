package com.github.dstaflund.nts.match.parent;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

@Path("/matching/parents")
@Produces(MediaType.APPLICATION_JSON)
public class MatchingParentsService {

    @GET
    public List<String> getMatchingParents(@QueryParam("query") String query){
        if (query == null || query.trim().isEmpty()) return Collections.emptyList();
        return MatchingParentsProvider.findMatchingSnippets(query.toUpperCase().trim() + "%");
    }
}

package com.github.dstaflund.nts.match.snippet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

@Path("/matching/snippets")
@Produces(MediaType.APPLICATION_JSON)
public class MatchingSnippetsService {

    @GET
    public List<String> getMatchingSnippets(@QueryParam("query") String query){
        if (query == null || query.trim().isEmpty()) return Collections.emptyList();
        return MatchingSnippetsProvider.findMatchingSnippets(query.toUpperCase().trim() + "%");
    }
}

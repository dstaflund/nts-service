package com.github.dstaflund.nts.match.name;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

@Path("/matching/names")
@Produces(MediaType.APPLICATION_JSON)
public class MatchingNamesService {

    @GET
    public List<String> getMatchingNames(@QueryParam("query") String query){
        if (query == null || query.trim().isEmpty()) return Collections.emptyList();
        return MatchingNamesProvider.findMatchingSnippets(query.toUpperCase().trim() + "%");
    }
}

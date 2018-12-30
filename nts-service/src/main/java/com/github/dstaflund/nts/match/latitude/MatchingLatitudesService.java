package com.github.dstaflund.nts.match.latitude;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

@Path("/matching/latitudes")
@Produces(MediaType.APPLICATION_JSON)
public class MatchingLatitudesService {

    @GET
    public List<Float> getMatchingLatitudes(@QueryParam("query") String query){
        if (query == null || query.trim().isEmpty()) return Collections.emptyList();
        return MatchingLatitudesProvider.findMatchingLatitudes(query);
    }
}

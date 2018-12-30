package com.github.dstaflund.nts.match.longitude;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

@Path("/matching/longitudes")
@Produces(MediaType.APPLICATION_JSON)
public class MatchingLongitudesService {

    @GET
    public List<Float> getMatchingLongitudes(@QueryParam("query") String query){
        if (query == null || query.trim().isEmpty()) return Collections.emptyList();
        return MatchingLongitudesProvider.findMatchingLongitudes(query);
    }
}

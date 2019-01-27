package com.github.dstaflund.nts.match.title;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/matching/titles")
@Produces(MediaType.APPLICATION_JSON)
public class MatchingTitlesService {

    @GET
    public List<String> getMatchingTitles(@Valid @BeanParam MatchingTitlesParams ctx){
        return MatchingTitlesProvider.findMatchingTitles(ctx);
    }
}

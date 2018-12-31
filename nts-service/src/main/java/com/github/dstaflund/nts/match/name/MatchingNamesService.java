package com.github.dstaflund.nts.match.name;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/matching/names")
@Produces(MediaType.APPLICATION_JSON)
public class MatchingNamesService {

    @GET
    public List<String> getMatchingNames(@Valid @BeanParam MatchingNamesParams ctx){
        return MatchingNamesProvider.findMatchingSnippets(ctx);
    }
}

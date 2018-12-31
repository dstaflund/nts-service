package com.github.dstaflund.nts.match.snippet;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/matching/snippets")
@Produces(MediaType.APPLICATION_JSON)
public class MatchingSnippetsService {

    @GET
    public List<String> getMatchingSnippets(@Valid @BeanParam MatchingSnippetsParams ctx){
        return MatchingSnippetsProvider.findMatchingSnippets(ctx);
    }
}

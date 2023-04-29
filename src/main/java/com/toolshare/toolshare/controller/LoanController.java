
package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.model.LoanAction;
import com.toolshare.toolshare.model.Participant;
import com.toolshare.toolshare.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/loan")
public class LoanController {

    /**
     * The loan service where the business logic takes place.
     */
    @Autowired
    private LoanService loanService;

    /**
     * Saves a new loan action to the database
     * for the specified participant and item.
     *
     * @param id the ID of the item to add a loan action to
     * @param addLoanAction the loan action to add
     * @return the newly saved loan action
     */
    @PostMapping("/participant/{id}")
    @Operation(summary = "This API posts a specific interest "
            + "in a share item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "interest indicated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description =
                            "Participant not found with the given id"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    public LoanAction saveLoanAction(
            @PathVariable final Long id,
            @RequestBody final LoanAction addLoanAction) {
        return loanService.saveLoanAction(id, addLoanAction);
    }

    /**
     * Gets a participant and their loan history by ID.
     *
     * @param id the ID of the participant to get
     * @return the participant with the specified ID and
     * their associated loan history
     */
    @GetMapping("/participants/loaninterest/{id}")
    @Operation(summary = "This API retrieves all interest "
            + "indications the participant has made")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "interest indications obtained successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description =
                            "Participant not found with the given id"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    public Participant getLoanActionById(@PathVariable final Long id) {
        return loanService.getLoanActionById(id);
    }
}

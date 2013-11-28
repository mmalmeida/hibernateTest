
    @org.hibernate.annotations.GenericGenerator(
        name="PK_TRIAL",
        strategy = "sequence",        
        parameters = {        		
            @Parameter(name="sequence", value="pk_clinical_entities"),
            @Parameter(name="schema", value="clinical")
        }
     )
package hibernateTest;

    import org.hibernate.annotations.Parameter;


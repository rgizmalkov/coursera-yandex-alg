package Utils.annotations;

/**
 * Created by romanizmalkov on 18.02.17.
 */
public class Scope {
   enum ScopeExecute {
       ALL("ALL TESTS"),
       ONE("FIRST TEST"),
       FIVE("FIRST FIVE TESTS");

       private String definition;

       ScopeExecute(String definition) {
           this.definition = definition;
       }
   }

    private Integer val;
    private ScopeExecute scope;

    public Scope(Integer val, ScopeExecute scope) {
        this.val = val;
        this.scope = scope;
    }

    public Integer getVal() {
        return val;
    }

    public ScopeExecute getScope() {
        return scope;
    }
}

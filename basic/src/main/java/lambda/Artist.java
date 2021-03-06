package lambda;

import java.util.Set;

/**
 * @author flyman
 */
public class Artist {

    private String name;

    private Set<String> memberNames;

    private String origin;

    public Artist(String name, Set<String> memberNames, String origin) {
        this.name = name;
        this.memberNames = memberNames;
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getMemberNames() {
        return memberNames;
    }

    public void setMemberNames(Set<String> memberNames) {
        this.memberNames = memberNames;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}

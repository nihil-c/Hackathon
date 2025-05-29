package model;

public class DefaultRole implements Role {
    @Override
    public String getRoleName() {
        return "User";
    }
}

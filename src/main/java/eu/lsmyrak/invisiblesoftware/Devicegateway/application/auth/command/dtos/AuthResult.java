package eu.lsmyrak.invisiblesoftware.Devicegateway.application.auth.command.dtos;

import java.util.ArrayList;
import java.util.List;

public class AuthResult {
    private boolean success;
    private String tokken;
    private List<String> errors = new ArrayList<>();

    public AuthResult(boolean b, String string, String tokken) {
        this.success = b;
        this.errors.add(string);
        this.tokken = tokken;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTokken() {
        return tokken;
    }

    public void setTokken(String tokken) {
        this.tokken = tokken;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
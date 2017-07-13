package org.fundacionjala.enforce.sonarqube.apex.checks.checkstyle.config;

public class Marker
{
    private String message;

    private String replacement;

    private String regex;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getReplacement ()
    {
        return replacement;
    }

    public void setReplacement (String replacement)
    {
        this.replacement = replacement;
    }

    public String getRegex ()
    {
        return regex;
    }

    public void setRegex (String regex)
    {
        this.regex = regex;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", replacement = "+replacement+", regex = "+regex+"]";
    }
}

package org.jcs.esjp.model;

public class StructureGate
    extends StructureAbstract
{
    private String fromID;
    private String toID;
    private Sector parent;

    public String getFromID()
    {
        return fromID;
    }
    public void setFromID(final String fromID)
    {
        this.fromID = fromID;
    }
    public String getToID()
    {
        return toID;
    }
    public void setToID(final String toID)
    {
        this.toID = toID;
    }
    @Override
    public Sector getParent()
    {
        return parent;
    }
    @Override
    public void setParent(final Sector parent)
    {
        this.parent = parent;
    }


}

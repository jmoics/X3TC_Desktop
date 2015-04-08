package org.jcs.esjp.model;

public abstract class StructureAbstract
{
    private String name;
    private Race race;
    private Integer posX;
    private Integer posY;
    private Integer posZ;
    private String iconPath;
    private Integer orderType;
    private Sector parent;

    public String getName()
    {
        return name;
    }
    public void setName(final String name)
    {
        this.name = name;
    }
    public Race getRace()
    {
        return race;
    }
    public void setRace(final Race race)
    {
        this.race = race;
    }
    public Integer getPosX()
    {
        return posX;
    }
    public void setPosX(final Integer posX)
    {
        this.posX = posX;
    }
    public Integer getPosY()
    {
        return posY;
    }
    public void setPosY(final Integer posY)
    {
        this.posY = posY;
    }
    public Integer getPosZ()
    {
        return posZ;
    }
    public void setPosZ(final Integer posZ)
    {
        this.posZ = posZ;
    }
    public String getIconPath()
    {
        return iconPath;
    }
    public void setIconPath(final String iconPath)
    {
        this.iconPath = iconPath;
    }
    public Integer getOrderType()
    {
        return orderType;
    }
    public void setOrderType(final Integer orderType)
    {
        this.orderType = orderType;
    }
    public Sector getParent()
    {
        return parent;
    }
    public void setParent(final Sector parent)
    {
        this.parent = parent;
    }
    @Override
    public String toString() {
        return this.name;
    };
}

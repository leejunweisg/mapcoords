package me.overtide.mapcoords.models;

public class Coord {
    int x;
    int y;
    int z;
    String name;
    String world;
    String owner;
    boolean isPublic;

    public Coord(int x, int y, int z, String name, String world, String owner, boolean isPublic) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
        this.world = world;
        this.owner = owner;
        this.isPublic = isPublic;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String getName() {
        return name;
    }

    public String getWorld() {
        return world;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}

package com.railwayteam.railways.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;

public abstract class VectorUtils {
    public static Vector3d opposite(BlockPos in) {
        return new Vector3d(in.getX() * -1, in.getY() * -1, in.getZ() * -1);
    }

    public enum Vector {
        NORTH(0, 0, -1, "n"),
        SOUTH(0, 0, 1, "s"),
        EAST(1, 0, 0, "e"),
        WEST(-1, 0, 0, "w"),
        NORTHWEST(-1, 0, -1, "nw"),
        NORTHEAST(1, 0, -1, "ne"),
        SOUTHWEST(-1, 0, 1, "sw"),
        SOUTHEAST(1, 0, 1, "se");

        public BlockPos value;
        public String name;

        private Vector(int x, int y, int z, String name) {
            value = new BlockPos(x, y, z);
            this.name = name;
        }

        public static Vector getClosest(BlockPos candidate) {
            return getClosest(new Vector3d(
                    Math.signum(Math.round(candidate.getX())),
                    0,
                    Math.signum(Math.round(candidate.getZ()))
            ));
        }

        public static Vector getClosest (Vector3d candidate) {
            for (Vector v : values()) {
                if (Integer.signum((int) candidate.getX()) != v.value.getX()) continue;
                if (Integer.signum((int) candidate.getZ()) != v.value.getZ()) continue;
                return v;
            }
            return SOUTH;
        }

        public static Vector getClosest (Vector3i candidate) {
            for (Vector v : values()) {
                if (Integer.signum(candidate.getX()) != v.value.getX()) continue;
                if (Integer.signum(candidate.getZ()) != v.value.getZ()) continue;
                return v;
            }
            return SOUTH;
        }

        public Vector getOpposite() {
            return getOpposite(this);
        }

        public static Vector getOpposite(Vector in) {
            switch (in) {
                case NORTH:
                    return SOUTH;
                case SOUTH:
                    return NORTH;
                case EAST:
                    return WEST;
                case WEST:
                    return EAST;
                case NORTHWEST:
                    return SOUTHEAST;
                case NORTHEAST:
                    return SOUTHWEST;
                case SOUTHWEST:
                    return NORTHEAST;
                case SOUTHEAST:
                    return NORTHWEST;
            }
            return SOUTH; // should never get here
        }
    }

    public static Vector3d blockPosToVector3d(BlockPos pos) {
        return new Vector3d(pos.getX(), pos.getY(), pos.getZ());
    }
}

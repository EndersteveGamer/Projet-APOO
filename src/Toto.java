public class Toto {
	public static void main(String[] args) {


	}

	static boolean isFree(Grid grid, Polyomino polyomino, Position select) {
		for (int x = 0; x < polyomino.getBlocks().length; x++) {
			for (int y = 0; y < polyomino.getBlocks()[0].length; y++) {
				if (polyomino.getBlocks()[x][y]) {
					Position res = new Position(select.getX() + x - polyomino.getCenterPosition().getX(), select.getY() + y - polyomino.getCenterPosition().getY());
					if (res.getX() < 0 || res.getY() < 0 || res.getY() > grid.getMatrix().length || res.getX() > grid.getMatrix()[0].length)
						return (false);
					else {
						if (grid.getMatrix()[x][y] != '.') return (false);
					}
				}
			}
		}
		return true;
	}
}
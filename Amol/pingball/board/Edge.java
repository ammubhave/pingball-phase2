package pingball.board;

/** The board edge next to this wall. */
public enum Edge {
	TOP {
		@Override
		public Edge opposite() { return BOTTOM; }
		@Override
		public boolean isHorizontal() { return true; }
	},
	RIGHT {
		@Override
		public Edge opposite() { return LEFT; }
		@Override
		public boolean isHorizontal() { return false; }
	},
	BOTTOM {
		@Override
		public Edge opposite() { return TOP; }
		@Override
		public boolean isHorizontal() { return true; }
	},
	LEFT {
		@Override
		public Edge opposite() { return RIGHT; }
		@Override
		public boolean isHorizontal() { return false; }
	};
	
	/**
	 * The opposite edge of the board.
	 * @return the opposite edge of the board
	 */
	public abstract Edge opposite();
	/**
	 * True for horizontal edges, false for vertical edges.
	 * @return true for horizontal edges, false for vertical edges
	 */
	public abstract boolean isHorizontal();
}
package breakout;


public class Levels {
	int level;
	int brickRows;
	int brickColumns;
	int timeInterval;
	
    public Levels(int level, int timeInterval, int brickRows, int brickColumns)
    {
      this.level = level;
      this.brickColumns = brickColumns;
      this.brickRows = brickRows;
      this.timeInterval = timeInterval;
    }

    public int getLevel()
    {
      return level;
    }
    public int getBrickRows()
    {
      return brickRows;
    }
    public int getBrickColumns()
    {
      return brickColumns;
    }
    public int gettimeInterval()
    {
      return timeInterval;
    }
    public void startLevel()
    {
        new Breakout(this,timeInterval,brickRows,brickColumns);
    }

}

public interface PQKey<Y>extends Comparable<Y>{
public int getPos();
public void setPos(int pos);

public Y pushToTop();
}

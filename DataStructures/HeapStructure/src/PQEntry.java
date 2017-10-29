
public interface PQEntry <T, Y extends PQKey<Y>> extends Comparable<T>{
public Y getKey();
void setKey(Y k);

}

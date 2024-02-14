/*
Розробити та протестувати клас Point. Реалізувати метод перевірки належності точки колу з центром в заданій точці та  заданого радіусу.

Перевизначити методи toString та equals.

В main створити масив об'єктів цього
класу, задати довільно координати (зробити так, щоб координати повторювалися) та вивести ті з них, які потрапили всередину кола з центром у точці (1, 2) та радіусом 5.

З клавіатури ввести довільну точку та підрахувати кількість точок у масиві рівних заданій.
*/
public class Point {
    public float x;
    public float y;
    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public boolean isInCircle(Point center, float radius) {
        return (float) Math.sqrt((this.x - center.x) * (this.x - center.x) + (this.y - center.y) * (this.y - center.y)) <= radius;
    }

    @Override
    public String toString() {
        return "Point (" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Point second_point = (Point) obj;
        return Float.compare(second_point.x, x) == 0 && Float.compare(second_point.y, y) == 0;
    }
}
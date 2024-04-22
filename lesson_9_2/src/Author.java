public class Author {
    private String name;
    private String surname;
    private int yearBirth;

    public Author() {}

    public Author(String name, String surname, int yearBirth) {
        this.name = name;
        this.surname = surname;
        this.yearBirth = yearBirth;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getYearBirth() {
        return yearBirth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setYearBirth(int yearBirth) {
        this.yearBirth = yearBirth;
    }

    @Override
    public String toString() {
        return "Author: " + name + ' ' + surname + " (" + yearBirth + ").";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Author)) {
            return false;
        }
        Author author = (Author) obj;
        return (author.getName().equals(this.getName())
                && author.getSurname().equals(this.getSurname())
                && author.getYearBirth() == this.getYearBirth());
    }
}

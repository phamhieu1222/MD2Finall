public class Contact {
    private String phoneNumber;
    private String group;
    private String name;
    private String gender;
    private String address;
    private String birthday;
    private String email;

    public Contact(String phoneNumber, String group, String name, String gender, String address, String birthday, String email) {
        this.phoneNumber = phoneNumber;
        this.group = group;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.birthday = birthday;
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Contact fromCSV(String line) {
        String[] parts = line.split(",");
        return new Contact(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
    }

    public String toCSV() {
        return String.join(",", phoneNumber, group, name, gender, address, birthday, email);
    }
}


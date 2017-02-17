public class User {

    public String firebaseUid;

    public String userEmail;

    public String profilePictureUrl;

    public String userName;

    public Boolean isDeleted;

    public User() {}

    public User(String firebaseUid, String userEmail, String userName, String profilePictureUrl) {
        this.firebaseUid = firebaseUid;
        this.userEmail = userEmail;
        this.userName = userName;
        this.profilePictureUrl = profilePictureUrl;
        this.isDeleted = false;
    }

    public User(String firebaseUid, String userEmail, String profilePictureUrl, String userName, Boolean isDeleted) {
        this.firebaseUid = firebaseUid;
        this.userEmail = userEmail;
        this.profilePictureUrl = profilePictureUrl;
        this.userName = userName;
        this.isDeleted = isDeleted;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("firebaseUid", firebaseUid);
        result.put("userEmail", userEmail);
        result.put("profilePictureUrl", profilePictureUrl);
        result.put("userName", userName);
        result.put("isDeleted", isDeleted);
        return result;
    }

    @Exclude
    public static User parseSnapshot(DataSnapshot snapshot){
        User user = new User();
        user.firebaseUid = (String) snapshot.child("firebaseUid").getValue();
        user.userEmail = (String) snapshot.child("userEmail").getValue();
        user.profilePictureUrl = (String) snapshot.child("profilePictureUrl").getValue();
        user.userName = (String) snapshot.child("userName").getValue();
        user.isDeleted = (Boolean) snapshot.child("isDeleted").getValue();

        return user;
    }
}

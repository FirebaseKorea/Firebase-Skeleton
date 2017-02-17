public class MainActivity extends BaseActivity implements View.OnClickListener {
    private final String TAG = MainActivity.class.getSimpleName();


    private String mFirebaseUid;
    private DatabaseReference mUserReference;
    private ValueEventListener mUserLister;

    private ArrayList<User> mUserList = new ArrayList<User>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 로컬 디바이스에 저장된 FirebaseUid 획득
        mFirebaseUid = UserUtil.loadUserFirebaseUid();
        // init firebase
        if (mFirebaseUid == null || FirebaseAuth.getInstance().getCurrentUser() == null){
            mUserReference = null;
        }
        else {
            mUserReference = FirebaseDatabase.getInstance().getReference().child("users");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUserList.clear();

                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    User user = User.parseSnapshot(child);
                    if (user.isDeleted != null && user.isDeleted == false) {
                        mUserList.add(user);
                    }
                }

                // 데이터를 다 받고 View update하는 시
                updateUserInterface();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadUserList:onCancelled", databaseError.toException());
                ToastUtil.makeShortToast(this, "유저 정보를를 일시적으로 불러올 수 없습니다");
            }
        };
        if(mUserReference != null) {
            mUserReference.addValueEventListener(userListener);
        }
        mUserLister = userListener;
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mUserLister != null) {
            if(mUserReference != null) {
                mUserReference.removeEventListener(mUserLister);
            }
        }
    }

    private void updateUserInterface() {
        // TODO : update user interface
    }
}

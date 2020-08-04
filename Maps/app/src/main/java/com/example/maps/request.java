package com.example.maps;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class request extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button btnFile, btnUpload;
    private EditText etFile;
    private ImageView imageView;
    private TextView tvShow;
    private ProgressBar progressBar;
    private EditText uname;
    private EditText phone;
    private TextView name;
    public static final int IMAGE_FILE_REQUEST = 1;
    private Uri imageUri;
    String n;
    private static final int REQUEST_IMAGE_CAPTURE= 1;
    private Button camera;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    //private FirebaseAuth mAuth;
    private StorageTask uploadTask;
    File photoFile;
    String value;
    Spinner s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        Intent intent = getIntent();
         n = intent.getStringExtra("name");
        getSupportActionBar().setTitle(n);

        System.out.println("chirag"+n);
        btnFile = findViewById(R.id.btn_file);
        btnUpload = findViewById(R.id.btn_upload);
        etFile = findViewById(R.id.et_file);
        imageView = findViewById(R.id.image_view);
        progressBar = findViewById(R.id.progress_bar);
        camera= findViewById(R.id.button_camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
//        name = n;
//        name.setText(n);
        String[] arraySpinner = new String[] {
        "Mc Aloo Tikki","Mc Veggie","Medium Fries","Medium Coke"        };

         s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(this);

        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");

        btnFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPhotoFromFileLocation();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(uploadTask != null && uploadTask.isInProgress()){
                    Toast.makeText(request.this, "Upload is in progress", Toast.LENGTH_LONG).show();
                }else{
                    System.out.println("here");
//                    EditText editText = (EditText) findViewById(R.id.Description);
//                     value = editText.getText().toString();
                    uploadImageToFirebase();

                }
            }
        });
    }
    String currentPhotoPath;
//    public void addListenerOnSpinnerItemSelection() {
//        s = (Spinner) findViewById(R.id.spinner);
//        s.setOnItemSelectedListener(new CustomOnItemSelectedListener());
//    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            photoFile = null;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {
//                System.out.println("NO file");
//            }
//            if (photoFile != null) {
//                imageUri = FileProvider.getUriForFile(this,
//                        "com.example.maps",
//                        photoFile);
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }


    private void getPhotoFromFileLocation(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_FILE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMAGE_FILE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            Glide.with(this).load(imageUri).into(imageView);
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data!=null && data.getData()!=null) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//
////            Bitmap imageBitmap = (Bitmap) extras.get("data");
////            Glide.with(this).load(imageUri).into(imageView);
//
//             imageView.setImageBitmap(imageBitmap);
        }
    }

    private String getExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void uploadImageToFirebase(){

        //Testing
        String keyId = databaseReference.push().getKey();
        System.out.println("Chiggy"+keyId);
//        FirebaseDatabase.getInstance().getReference().child(keyId).setValue("Hello");

        if(imageUri!=null){
            System.out.println("Uload");

            StorageReference fileUploadRef = storageReference.child(System.currentTimeMillis() + "."+getExtension(imageUri));
            System.out.println("Uload2");

            uploadTask = fileUploadRef.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            System.out.println("Uload3");

                            Toast.makeText(request.this, "File Uploaded", Toast.LENGTH_SHORT).show();
                            //save the metadata of the uploaded image is stored in the database with name and download url

                            Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                            task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String photoLink = uri.toString();
                                    String key = databaseReference.push().getKey();
                                    Upload upload = new Upload(n,photoLink,value);
                                    databaseReference.child(key).setValue(upload);

                                }
                            });

                            progressBar.setProgress(0);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(request.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            System.out.println("hua");
                            double pr = taskSnapshot.getBytesTransferred()*100/taskSnapshot.getTotalByteCount();
                            progressBar.setProgress((int)pr);
                        }
                    });

        }else{
            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        value = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

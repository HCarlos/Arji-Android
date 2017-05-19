package mx.com.logydes.colegioarji;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import mx.com.logydes.colegioarji.Pojos.Sistema_Info;

import static mx.com.logydes.colegioarji.R.string.token_info;

public class SystemActivity extends AppCompatActivity {
    private Sistema_Info sinfo;
    private AutoCompleteTextView token;
    private AutoCompleteTextView token_id;
    private AutoCompleteTextView iduser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);
        this.setTitle(R.string.action_system);

        sinfo = new Sistema_Info();

        token = (AutoCompleteTextView) findViewById(R.id.token_info);
        token.setText(sinfo.getToken());

        token_id = (AutoCompleteTextView) findViewById(R.id.token_id_info);
        token_id.setText(sinfo.getToken_ID());

        iduser = (AutoCompleteTextView) findViewById(R.id.iduser_info);
        iduser.setText(String.valueOf(sinfo.getIdUser()));

    }

}

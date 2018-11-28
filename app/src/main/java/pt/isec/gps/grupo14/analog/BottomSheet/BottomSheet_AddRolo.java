package pt.isec.gps.grupo14.analog.BottomSheet;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import pt.isec.gps.grupo14.analog.AnaLog.Rolo;
import pt.isec.gps.grupo14.analog.R;
import pt.isec.gps.grupo14.analog.RolosActivity;
import pt.isec.gps.grupo14.analog.SplashActivity;

public class BottomSheet_AddRolo extends BottomSheetDialogFragment {

    private boolean open;

    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_add_rolo, container, false);
        Button button = (Button) v.findViewById(R.id.Btn_New_Rolo);
        final TextInputEditText Titulo = (TextInputEditText)v.findViewById(R.id.Titulo_New_Rolo);
        final TextInputEditText Camera = (TextInputEditText)v.findViewById(R.id.IDCAM_New_Rolo);
        final TextInputEditText ISO = (TextInputEditText)v.findViewById(R.id.ISO_New_Rolo);
        final TextInputEditText Nexp = (TextInputEditText)v.findViewById(R.id.NExp_New_Rolo);
        final TextInputEditText Formato = (TextInputEditText)v.findViewById(R.id.Formato_New_Rolo);
        final TextInputEditText Desc = (TextInputEditText)v.findViewById(R.id.Desc_New_Rolo);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //guardar rolo

                Rolo r = new Rolo(Titulo.getText().toString(), Integer.parseInt(ISO.getText().toString()),
                        Integer.parseInt(Formato.getText().toString()),
                        Integer.parseInt(Nexp.getText().toString()),Desc.getText().toString(),
                        Integer.parseInt(Camera.getText().toString()), getContext());
                dismiss();
                Intent intent = new Intent(getContext(),RolosActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return v;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new BottomSheetDialog(getContext(), getTheme());
    }

    @Override
    public int getTheme() {
        return R.style.BaseBottomSheetDialog;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
        open=true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        open=false;
    }

    public boolean isOpen() {
        return open;
    }
}

package pt.isec.gps.grupo14.analog.BottomSheet;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import androidx.recyclerview.widget.RecyclerView;
import pt.isec.gps.grupo14.analog.AnaLog.Exposicao;
import pt.isec.gps.grupo14.analog.AnaLog.Rolo;
import pt.isec.gps.grupo14.analog.ExposicaoActivity;
import pt.isec.gps.grupo14.analog.R;
import pt.isec.gps.grupo14.analog.RolosActivity;
import pt.isec.gps.grupo14.analog.SplashActivity;

public class BottomSheet_AddExp extends BottomSheetDialogFragment {

    private boolean open;
    int IDRolo;



    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_add_exp, container, false);
        Button button = (Button) v.findViewById(R.id.Btn_New_Exp);
        IDRolo = getArguments().getInt("IDrolo");
        final TextInputEditText abertura = (TextInputEditText)v.findViewById(R.id.Abertura_New_Exp);
        final TextInputEditText Vel = (TextInputEditText)v.findViewById(R.id.VelD__New_Exp);
        final TextInputEditText Lente = (TextInputEditText)v.findViewById(R.id.Lente_New_Exp);
        final TextInputEditText DistF = (TextInputEditText)v.findViewById(R.id.DistF_New_Exp);
        final TextInputEditText Desc = (TextInputEditText)v.findViewById(R.id.Desc_New_Exp);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //guardar Exp

                Exposicao r = new Exposicao(Integer.parseInt(Vel.getText().toString()), Float.parseFloat(abertura.getText().toString()),
                        Integer.parseInt(DistF.getText().toString()),
                        Desc.getText().toString(), IDRolo ,
                        Integer.parseInt(Lente.getText().toString()), getContext());

                //((RecyclerView)getActivity().findViewById(R.id.lista_rolos)).invalidate();

                dismiss();
                getActivity().finish();
                Intent intent = new Intent(getContext(),ExposicaoActivity.class);
                intent.putExtra("idrolo", r.getIdRolo());
                startActivity(intent);

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

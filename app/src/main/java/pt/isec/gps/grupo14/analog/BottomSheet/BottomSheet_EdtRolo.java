package pt.isec.gps.grupo14.analog.BottomSheet;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import pt.isec.gps.grupo14.analog.AnaLog.Rolo;
import pt.isec.gps.grupo14.analog.DataBase.DBHandler;
import pt.isec.gps.grupo14.analog.ExposicaoActivity;
import pt.isec.gps.grupo14.analog.ListaRolosAdapter;
import pt.isec.gps.grupo14.analog.R;
import pt.isec.gps.grupo14.analog.RolosActivity;

public class BottomSheet_EdtRolo extends BottomSheetDialogFragment {

    private boolean open;
    Rolo rolo;

    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_edt_rolo, container, false);
        Button button = (Button) v.findViewById(R.id.Btn_Edt_Rolo);
        final DBHandler db = new DBHandler(getContext());

        int id =getArguments().getInt("IDRolo");
        rolo = db.getRolo(id);

        final TextInputEditText Titulo = (TextInputEditText)v.findViewById(R.id.Titulo_Edt_Rolo);
        final TextInputEditText Camera = (TextInputEditText)v.findViewById(R.id.IDCAM_Edt_Rolo);
        final TextInputEditText ISO = (TextInputEditText)v.findViewById(R.id.ISO_Edt_Rolo);
        final TextInputEditText Nexp = (TextInputEditText)v.findViewById(R.id.NExp_Edt_Rolo);
        final TextInputEditText Formato = (TextInputEditText)v.findViewById(R.id.Formato_Edt_Rolo);
        final TextInputEditText Desc = (TextInputEditText)v.findViewById(R.id.Desc_Edt_Rolo);
        final Switch revelado= (Switch)v.findViewById(R.id.ReveladoSwitch) ;

        Titulo.setText(rolo.getTitulo());
        Camera.setText(Integer.toString(rolo.getIdCamera()));
        ISO.setText(Integer.toString(rolo.getIso()));
        Nexp.setText(Integer.toString(rolo.getMaxExposicoes()));
        Formato.setText(Integer.toString(rolo.getFormato()));
        Desc.setText(rolo.getDescricao());
        revelado.setChecked(rolo.isRevelado());



        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //update rolo

                rolo.updateRolo(Titulo.getText().toString(), Integer.parseInt(ISO.getText().toString()),
                        Integer.parseInt(Formato.getText().toString()),
                        Integer.parseInt(Nexp.getText().toString()),Desc.getText().toString(), revelado.isChecked() ,
                        Integer.parseInt(Camera.getText().toString()), getContext());

                Intent intent = new Intent(getContext(),ExposicaoActivity.class);
                intent.putExtra("idrolo", rolo.getIdRolo());
                startActivity(intent);
                getActivity().finish();
                dismiss();
               // ((ListaRolosAdapter)((RecyclerView)(getActivity().findViewById(R.id.lista_rolos))).getAdapter()).addNewRolo(r);
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



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
import pt.isec.gps.grupo14.analog.AnaLog.Exposicao;
import pt.isec.gps.grupo14.analog.AnaLog.Rolo;
import pt.isec.gps.grupo14.analog.DataBase.DBHandler;
import pt.isec.gps.grupo14.analog.ExposicaoActivity;
import pt.isec.gps.grupo14.analog.R;
import pt.isec.gps.grupo14.analog.RolosActivity;

public class BottomSheet_EdtExp extends BottomSheetDialogFragment {

    private boolean open;
    Exposicao exp;

    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_edt_exp, container, false);
        Button button = (Button) v.findViewById(R.id.Btn_Edt_Exp);
        Button delete =(Button) v.findViewById(R.id.Btn_Del_Exp);
        final DBHandler db = new DBHandler(getContext());

        int id =getArguments().getInt("IDExp");
        exp = db.getExposicao(id);

        final TextInputEditText abertura = (TextInputEditText)v.findViewById(R.id.Abertura_Edt_Exp);
        final TextInputEditText Vel = (TextInputEditText)v.findViewById(R.id.VelD_Edt_Exp);
        final TextInputEditText Lente = (TextInputEditText)v.findViewById(R.id.Lente_Edt_Exp);
        final TextInputEditText DistF = (TextInputEditText)v.findViewById(R.id.DistF_Edt_Exp);
        final TextInputEditText Desc = (TextInputEditText)v.findViewById(R.id.Desc_Edt_Exp);

        abertura.setText(Float.toString(exp.getAbertura()));
        Vel.setText(Integer.toString(exp.getVelDisparo()));
        Lente.setText(Integer.toString(exp.getIdObjetiva()));
        DistF.setText(Integer.toString(exp.getDistFocal()));
        Desc.setText(exp.getDescricao());


        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //update rolo

                db.removeExposicao(exp.getIdExposicao());
                dismiss();
                Intent intent = new Intent(getContext(),ExposicaoActivity.class);
                intent.putExtra("idrolo", exp.getIdRolo());
                startActivity(intent);
                getActivity().finish();

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //update rolo

                exp.updateExposicao(Desc.getText().toString(), Integer.parseInt(Vel.getText().toString()),
                        Float.parseFloat(abertura.getText().toString()),Integer.parseInt(DistF.getText().toString()),
                        Integer.parseInt(Lente.getText().toString()), getContext());

                Intent intent = new Intent(getContext(),ExposicaoActivity.class);
                intent.putExtra("idrolo", exp.getIdRolo());
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

package pt.isec.gps.grupo14.analog.BottomSheet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import pt.isec.gps.grupo14.analog.AnaLog.Exposicao;
import pt.isec.gps.grupo14.analog.AnaLog.Objetiva;
import pt.isec.gps.grupo14.analog.DataBase.DBHandler;
import pt.isec.gps.grupo14.analog.ExposicaoActivity;
import pt.isec.gps.grupo14.analog.R;

public class BottomSheet_AddExp extends BottomSheetDialogFragment {

    private boolean open;
    int IDRolo;



    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }


    private View.OnClickListener btnListenerLente = new View.OnClickListener()
    {

        public void onClick(View view)
        {
            final View v = view;
            DBHandler db = new DBHandler(getContext());
            final ArrayList<Objetiva> Objetivas;
            try {
                Objetivas = new ArrayList<>(db.getObjetivas().values());
            }catch (NullPointerException e){

                Log.e("DEBUG:", "Sem objectivas na lista");
                return;
            }
            final ArrayList<String> ObjNames = new ArrayList<String>();

            for (int i=0; i<Objetivas.size(); i++)
            {
                ObjNames.add((Objetivas.get(i).getMarca()+ " "+Objetivas.get(i).getModelo()));
            }

            AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
            ad.setTitle(getString(R.string.Cam_escolha));

            ad.setSingleChoiceItems(ObjNames.toArray(new String[ObjNames.size()]), -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ((TextInputEditText)v.findViewById(R.id.Lente_New_Exp)).setText(ObjNames.get(which));
                    dialog.dismiss();
                }
            });

            ad.show();
        }

    };
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
        final TextInputLayout Aberturalayout = (TextInputLayout)v.findViewById(R.id.AberturaLayout);
        final TextInputLayout VelDlayout = (TextInputLayout)v.findViewById(R.id.VelDLayout);
        final TextInputLayout Desclayout = (TextInputLayout)v.findViewById(R.id.DescLayout);

        Lente.setOnClickListener(btnListenerLente);

        abertura.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Aberturalayout.setError(null);
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //guardar Exp

                int control=0;
                if(abertura.getText().toString().isEmpty())
                {
                    Aberturalayout.setError(getString(R.string.Erro_Abr));
                    control=1;
                    abertura.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (s.length() > 0) {
                                Aberturalayout.setError(null);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
                if(Vel.getText().toString().isEmpty())
                {
                    VelDlayout.setError(getString(R.string.Erro_Vel));
                    control=1;
                    Vel.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (s.length() > 0) {
                                VelDlayout.setError(null);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
                if(Desc.getText().toString().isEmpty())
                {
                    Desclayout.setError(getString(R.string.Erro_desc));
                    Desc.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (s.length() > 0) {
                                Desclayout.setError(null);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    control=1;
                }

                if (control==1)
                    return;


                String lente="";
                int distFocal=0;

                if (!Lente.getText().toString().isEmpty()){
                    lente= Lente.getText().toString();
                }
                if (!DistF.getText().toString().isEmpty()){
                    distFocal= Integer.parseInt(DistF.getText().toString());
                }
                Exposicao r = new Exposicao(Integer.parseInt(Vel.getText().toString()), Float.parseFloat(abertura.getText().toString()),
                        distFocal,
                        Desc.getText().toString(), IDRolo ,
                        lente, getContext());

                //((RecyclerView)getActivity().findViewById(R.id.lista_rolos)).invalidate();

                dismiss();
                getActivity().finish();
                Intent intent = new Intent(getContext(),ExposicaoActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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

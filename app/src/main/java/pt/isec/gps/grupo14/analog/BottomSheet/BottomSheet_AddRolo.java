package pt.isec.gps.grupo14.analog.BottomSheet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import pt.isec.gps.grupo14.analog.AnaLog.Camera;
import pt.isec.gps.grupo14.analog.AnaLog.Rolo;
import pt.isec.gps.grupo14.analog.DataBase.DBHandler;
import pt.isec.gps.grupo14.analog.ListaRolosAdapter;
import pt.isec.gps.grupo14.analog.R;

public class BottomSheet_AddRolo extends BottomSheetDialogFragment {

    private boolean open;
    View v;

    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }


    private View.OnClickListener btnListenerISO = new View.OnClickListener()
    {

        public void onClick(View view)
        {
            final String[] isos = getResources().getStringArray(R.array.ISO_values);
            final View v = view;
            AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
            ad.setTitle(getString(R.string.Escolha_ISO));

            ad.setSingleChoiceItems(R.array.ISO_values, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ((TextInputEditText)v.findViewById(R.id.ISO_New_Rolo)).setText(isos[which]);
                    dialog.dismiss();
                }
            });

            ad.show();
        }

    };
    private View.OnClickListener btnListenerCam = new View.OnClickListener()
    {

        public void onClick(View view)
        {
            final View v = view;
            DBHandler db = new DBHandler(getContext());
            final ArrayList<Camera> Cameras;
            try{
                Cameras = new ArrayList<>(db.getCameras().values());
            }catch (NullPointerException e)
            {
                Log.e("DEBUG:", "Sem Maquinas na lista");
                return;
            }
            final ArrayList<String> CameraNames = new ArrayList<String>();

            for (int i=0; i<Cameras.size(); i++)
            {
                CameraNames.add((Cameras.get(i).getMarca()+ " "+Cameras.get(i).getModelo()));
            }

            AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
            ad.setTitle(getString(R.string.Cam_escolha));

            ad.setSingleChoiceItems(CameraNames.toArray(new String[CameraNames.size()]), -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ((TextInputEditText)v.findViewById(R.id.IDCAM_New_Rolo)).setText(CameraNames.get(which));
                    dialog.dismiss();
                }
            });

            ad.show();
        }

    };
    private View.OnClickListener btnListenerForm = new View.OnClickListener()
    {

        public void onClick(View view)
        {
            final String[] formatos = getResources().getStringArray(R.array.formato_values);
            final View v = view;
            AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
            ad.setTitle(getString(R.string.Escolha_forma));

            ad.setSingleChoiceItems(R.array.formato_values, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ((TextInputEditText)v.findViewById(R.id.Formato_New_Rolo)).setText(formatos[which]);
                    dialog.dismiss();
                }
            });

            ad.show();
        }

    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.bottom_sheet_add_rolo, container, false);
        Button button = (Button) v.findViewById(R.id.Btn_New_Rolo);
        final TextInputEditText Titulo = (TextInputEditText)v.findViewById(R.id.Titulo_New_Rolo);
        final TextInputEditText Camera = (TextInputEditText)v.findViewById(R.id.IDCAM_New_Rolo);
        final TextView ISO = (TextInputEditText)v.findViewById(R.id.ISO_New_Rolo);
        final TextInputEditText Nexp = (TextInputEditText)v.findViewById(R.id.NExp_New_Rolo);
        final TextInputEditText Formato = (TextInputEditText)v.findViewById(R.id.Formato_New_Rolo);
        final TextInputEditText Desc = (TextInputEditText)v.findViewById(R.id.Desc_New_Rolo);
        final TextInputLayout Titulolayout = (TextInputLayout)v.findViewById(R.id.TituloNewLayout);
        final TextInputLayout ISOlayout = (TextInputLayout)v.findViewById(R.id.ISONewLayout);
        final TextInputLayout NExplayout = (TextInputLayout)v.findViewById(R.id.NExpNewLayout);
        final TextInputLayout Formatoinput_layout = (TextInputLayout)v.findViewById(R.id.FormatoNewLayout);
        final TextInputLayout Descinput_layout = (TextInputLayout)v.findViewById(R.id.descricaoNewLayout);

        ISO.setOnClickListener(btnListenerISO);
        Camera.setOnClickListener(btnListenerCam);
        Formato.setOnClickListener(btnListenerForm);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int control=0;
                if(Titulo.getText().toString().isEmpty())
                {
                    Titulolayout.setError(getString(R.string.Erro_titulo));
                    control=1;
                    Titulo.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (s.length() > 0) {
                                Titulolayout.setError(null);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
                if(ISO.getText().toString().isEmpty())
                {
                    ISOlayout.setError(getString(R.string.Erro_ISO));
                    control=1;
                    ISO.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (s.length() > 0) {
                                ISOlayout.setError(null);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
                if(Nexp.getText().toString().isEmpty())
                {
                    NExplayout.setError(getString(R.string.Erro_nexp));
                    control=1;
                    Nexp.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (s.length() > 0) {
                                NExplayout.setError(null);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
                if(Formato.getText().toString().isEmpty())
                {
                    Formatoinput_layout.setError(getString(R.string.Erro_form));
                    control=1;
                    Formato.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.length() > 0) {
                            Formatoinput_layout.setError(null);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                }
                if(Desc.getText().toString().isEmpty())
                {
                    Descinput_layout.setError(getString(R.string.Erro_desc));
                    control=1;
                    Desc.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (s.length() > 0) {
                                Descinput_layout.setError(null);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }

                if (control==1) {
                    return;
                }

                String cam="";
                if (!Camera.getText().toString().isEmpty()){
                    cam= Camera.getText().toString();
                }
                Rolo r = new Rolo(Titulo.getText().toString(), Integer.parseInt(ISO.getText().toString()),
                        Integer.parseInt(Formato.getText().toString()),
                        Integer.parseInt(Nexp.getText().toString()),Desc.getText().toString(),
                        cam, getContext());

                Titulo.setText("");
                Camera.setText("");
                ISO.setText("");
                Nexp.setText("");
                Formato.setText("");
                Desc.setText("");

                dismiss();
                ((ListaRolosAdapter)((RecyclerView)(getActivity().findViewById(R.id.lista_rolos))).getAdapter()).addNewRolo(r);
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


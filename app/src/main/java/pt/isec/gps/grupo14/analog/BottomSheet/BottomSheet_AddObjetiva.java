package pt.isec.gps.grupo14.analog.BottomSheet;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import pt.isec.gps.grupo14.analog.AnaLog.Camera;
import pt.isec.gps.grupo14.analog.AnaLog.Objetiva;
import pt.isec.gps.grupo14.analog.PreferenciasAdapters.ListaCamerasAdapter;
import pt.isec.gps.grupo14.analog.PreferenciasAdapters.ListaObjetivasAdapter;
import pt.isec.gps.grupo14.analog.R;

public class BottomSheet_AddObjetiva extends BottomSheetDialogFragment {

    private boolean open;
    private View v;

    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.bottom_sheet_add_objetiva, container, false);
        Button button = v.findViewById(R.id.btn_new_Objetiva);
        final TextInputLayout marcaLayout = v.findViewById(R.id.newMarcaLayout);
        final TextInputEditText marca = v.findViewById(R.id.newMarcaText);
        final TextInputLayout modeloLayout = v.findViewById(R.id.newModeloLayout);
        final TextInputEditText modelo = v.findViewById(R.id.newModeloText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean controlo = false;

                if(marca.getText().toString().isEmpty()){
                    marcaLayout.setError(getString(R.string.erroEquipamento));
                    controlo = true;
                    marca.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if(s.length() > 0){
                                marcaLayout.setError(null);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
                if(modelo.getText().toString().isEmpty()){
                    modeloLayout.setError(getString(R.string.erroEquipamento));
                    controlo = true;
                    marca.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if(marca.length() > 0){
                                modeloLayout.setError(null);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }

                if(controlo == true){
                    return;
                }

                Objetiva obj = new Objetiva(marca.getText().toString(), modelo.getText().toString(), getContext());

                marca.setText("");
                modelo.setText("");

                dismiss();

                ((ListaObjetivasAdapter)((RecyclerView)(getActivity().findViewById(R.id.lista_objetivas))).getAdapter()).addNewObjetiva(obj);
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
        open = true;
    }

    public boolean isOpen() {
        return open;
    }
}

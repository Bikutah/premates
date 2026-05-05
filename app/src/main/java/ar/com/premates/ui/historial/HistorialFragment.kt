package ar.com.premates.ui.historial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.premates.R
import ar.com.premates.databinding.FragmentHistorialBinding
import ar.com.premates.databinding.ItemPartidaBinding
import ar.com.premates.model.historial.Partida
import kotlinx.coroutines.launch

class HistorialFragment : Fragment() {

    private var _binding: FragmentHistorialBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HistorialViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistorialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarLista()
        observarPartidas()
    }

    private fun configurarLista() {
        binding.rvPartidas.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observarPartidas() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.partidas.collect { listaPartidas ->
                binding.rvPartidas.adapter = AdaptadorPartidas(listaPartidas)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // ── Adapter dentro del mismo archivo ──────────────────────────

    inner class AdaptadorPartidas(
        private val partidas: List<Partida>
    ) : RecyclerView.Adapter<AdaptadorPartidas.PartidaViewHolder>() {

        inner class PartidaViewHolder(
            private val binding: ItemPartidaBinding
        ) : RecyclerView.ViewHolder(binding.root) {

            fun bind(partida: Partida) {
                binding.tvCampeon.text = partida.campeon
                binding.tvKda.text = "${partida.kills} / ${partida.muertes} / ${partida.asistencias}"
                binding.tvDuracion.text = "${partida.duracionMinutos}m"
                binding.tvResultado.text = partida.resultado
                aplicarColorResultado(partida.resultado)
            }

            private fun aplicarColorResultado(resultado: String) {
                val contexto = binding.root.context
                if (resultado == "Victoria") {
                    binding.tvResultado.setTextColor(contexto.getColor(R.color.victoria))
                    binding.root.setBackgroundColor(contexto.getColor(R.color.victoria_fondo))
                } else {
                    binding.tvResultado.setTextColor(contexto.getColor(R.color.derrota))
                    binding.root.setBackgroundColor(contexto.getColor(R.color.derrota_fondo))
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartidaViewHolder {
            val binding = ItemPartidaBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return PartidaViewHolder(binding)
        }

        override fun onBindViewHolder(holder: PartidaViewHolder, position: Int) {
            holder.bind(partidas[position])
        }

        override fun getItemCount() = partidas.size
    }
}
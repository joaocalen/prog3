import java.util.Comparator;

public class ComparadorNome implements Comparator<Ppg_Instituicao> {

	@Override
	public int compare(Ppg_Instituicao o1, Ppg_Instituicao o2) {
		return o1.getNomePpg().compareTo(o2.getNomePpg());		
	}
}

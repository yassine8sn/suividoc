package ma.uit.doctorants.services.graphe;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.uit.doctorants.repositories.ActeurRepository;
import ma.uit.doctorants.repositories.AvancementRepository;

@Service
public class reponseGrapheService {
	
	@Autowired
	ActeurRepository ac;
	@Autowired
	AvancementRepository avn;
	
	@Transactional
	public List<labelData> getAllDoc(String Enc){
		List<labelData> j=new ArrayList<>();
		List<String> cinDoc= ac.getListDocBy(Enc);
		
		for (String string : cinDoc) {
			labelData set=new labelData();
			set.setLabel(ac.findByCin(string).getNom());
			set.setData(avn.getMoisNoteDe(string));
			j.add(set);
		}
		j.add(setlabelData());
		return j;
	}
	@Transactional
	public List<labelData> getDoc(String Doc){
		List<labelData> j=new ArrayList<>();
		labelData set=new labelData();
		set.setLabel(Doc);
		set.setData(avn.getMoisNoteDe(Doc));
		j.add(set);
		j.add(setlabelData());
		return j;
	}

	public labelData setlabelData(){
		
		
		List<Object[]> data=new ArrayList<Object[]>();
		for(int i=1;i<=12;i++){
			Object[] noteMois = new Integer[2];
			noteMois[0]=i;
			noteMois[1]=0;
			data.add(noteMois);
		}
		labelData ld=new labelData();
		ld.setData((List<Object[]>)data);
		ld.setLabel("");
		return ld;
	} 
	
	

}

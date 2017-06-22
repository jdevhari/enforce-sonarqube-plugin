trigger accountTestTrggr on Account (before insert, before update) {
for(Account a : Trigger.New){
     	 
   //Error - hardcoded the record type id
   if(a.RecordTypeId=='012500000009WAr'){     	  	
      //do some logic here.....
   }else if(a.RecordTypeId=='0123000000095Km'){
      //do some logic here for a different record type...
   }
    	 
}
}
function editformsubmit(){
    if(checkInput()){
        if(checkLen()){
            editform.submitb.disabled=true;
            editform.submit();
        }
    }
}
function checkInput(){
    var incontent=editform.content.value;
    if(incontent==null||incontent.length==0){
        document.all.checkmessage.innerHTML="●请输入内容！";
        editform.content.focus();
        return false;
    }
    return true;
}
function checkLen(){
    var fieldName=editform.content;
    var maxlen=document.all.ContentAll.innerText;
    var useName=document.all.ContentUse;
    var remName=document.all.ContentRem;
    var inlen=fieldName.value.length;

    if(inlen>maxlen){
        fieldName.value=(fieldName.value).substring(0,maxlen);
        document.all.checkmessage.innerHTML="●内容最多允许输入"+maxlen+"个字符！";
        return false;
    }
    else{
        useName.innerText=eval(fieldName.value.length);
        remName.innerText=maxlen-useName.innerText;
        document.all.checkmessage.innerHTML="";
        return true;
    }
}
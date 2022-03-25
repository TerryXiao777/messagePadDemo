function myloginsubmit(){
    if(validateForm()){
        loginform.submitb.disabled=true;
        loginform.submit();
    }
}
/*
function document.onkeypress(){
    if(event.keyCode==13)
        myloginsubmit();
}
*/
function validateForm() {
    var name=loginform.name.value;
    var pswd=loginform.pswd.value
    if (name==null||name==""){
        document.all.checkmessage.innerText="●请输入用户名！";
        loginform.name.focus();
        return false;
    }
    if (pswd==null||pswd==""){
        document.all.checkmessage.innerText="●请输入密码！";
        loginform.pswd.focus();
        return false;
    }
    return true;
}
function clearmessage(){
    document.all.checkmessage.innerText="";
}
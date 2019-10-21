
function myEncrypt(){
    var input = document.getElementById("password_old").value;
    var encrypt = new JSEncrypt();
    encrypt.setPublicKey('MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCRRjobcdpzMY7UgRP4H/7936Ui09qQndfKkzpzmKzo1X30SGI6DDScYF0o8KdP/wK/AY92/V6LL1Fw0dneRd78J9QXT9vS8k3+32a2KP0K8MmBZME9LwORR9IvARpDzTmX5+c/KWjHnPpITHtCtzIodC/8c5kIHKbVcuj44ANRWQIDAQAB');
    var output = encrypt.encrypt(input);
    return output;
}
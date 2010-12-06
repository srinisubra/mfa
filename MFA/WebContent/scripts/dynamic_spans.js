function exchange(el){
	
	var ie=document.all&&!document.getElementById? document.all : 0;
	var toObjId=/b$/.test(el.id)? el.id.replace(/b$/,'') : el.id+'b';
	var toObj=ie? ie[toObjId] : document.getElementById(toObjId);
	if(/b$/.test(el.id))
		toObj.innerHTML=el.value;
	else{
		toObj.style.width=el.offsetWidth+7+'px';
		toObj.value=el.innerHTML;
	}
	el.style.display='none';
	toObj.style.display='inline';
}


function replaceButtonText(buttonId, text)
{
	
	if (document.getElementById)
	{
		var button=document.getElementById(buttonId);
		if (button)
		{
			if (text == "Edit Profile")
			{
				//alert("ch");		
				button.value = "Done";
				exchange(document.getElementById('fnamelabel'));
				exchange(document.getElementById('lnamelabel'));
				exchange(document.getElementById('empid'));
				exchange(document.getElementById('addr1'));
				exchange(document.getElementById('city'));
				exchange(document.getElementById('state'));
				exchange(document.getElementById('zip'));
				exchange(document.getElementById('country'));
				exchange(document.getElementById('phone13'));
				exchange(document.getElementById('phone23'));
				exchange(document.getElementById('phone34'));
				exchange(document.getElementById('email'));
				exchange(document.getElementById('jdatem'));
				exchange(document.getElementById('jdated'));
				exchange(document.getElementById('jdatey'));
				exchange(document.getElementById('ssn13'));
				exchange(document.getElementById('ssn23'));
				exchange(document.getElementById('ssn34'));
				exchange(document.getElementById('cardno'));
				exchange(document.getElementById('cardlimit'));

				

				

			}
			else if (text == "Done")
			{
				//alert("val");		
				button.value= "Edit Profile";
				exchange(document.getElementById('fnamelabelb'));
				exchange(document.getElementById('lnamelabelb'));
				exchange(document.getElementById('empidb'));
				exchange(document.getElementById('addr1b'));
				exchange(document.getElementById('cityb'));	
				exchange(document.getElementById('stateb'));
				exchange(document.getElementById('zipb'));
				exchange(document.getElementById('countryb'));				
				exchange(document.getElementById('phone13b'));
				exchange(document.getElementById('phone23b'));
				exchange(document.getElementById('phone34b'));
				exchange(document.getElementById('emailb'));
				exchange(document.getElementById('jdatemb'));
				exchange(document.getElementById('jdatedb'));
				exchange(document.getElementById('jdateyb'));
				exchange(document.getElementById('ssn13b'));
				exchange(document.getElementById('ssn23b'));
				exchange(document.getElementById('ssn34b'));
				exchange(document.getElementById('cardnob'));
				exchange(document.getElementById('cardlimitb'));
				
				//alert(getquerystring());
				xmlhttpPost('updateProfile');
			}
			
		}
	}
}

function xmlhttpPost(strURL) {
    var xmlHttpReq = false;
    var self = this;
    // Mozilla/Safari
    if (window.XMLHttpRequest) {
        self.xmlHttpReq = new XMLHttpRequest();
    }
    // IE
    else if (window.ActiveXObject) {
        self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
    }
    self.xmlHttpReq.open('POST', strURL, true);
    self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    self.xmlHttpReq.onreadystatechange = function() {
        if (self.xmlHttpReq.readyState == 4) {
            updatepage(self.xmlHttpReq.responseText);
        }
    }
    self.xmlHttpReq.send(getquerystring());
}

function getquerystring() {
	var form = document.getElementById('form69');
	var query = '';
	query += 'f_name='+escape(form.elements['fnamelabelb'].value.replace(/^\s*|\s*$/g,''))+'&';
	query += 'l_name=' + escape(form.elements['lnamelabelb'].value.replace(/^\s*|\s*$/g,''))+'&';
	query += 'employee_id=' + escape(form.elements['empidb'].value.replace(/^\s*|\s*$/g,''))+'&';
	query += 'street_address=' + escape(form.elements['addr1b'].value)+'&';
	query += 'city=' + escape(form.elements['cityb'].value.replace(/^\s*|\s*$/g,''))+'&';	
	query += 'state=' + escape(form.elements['stateb'].value)+'&';
	query += 'zip_code=' + escape(form.elements['zipb'].value.replace(/^\s*|\s*$/g,''))+'&';
	query += 'country=' + escape(form.elements['countryb'].value)+'&';				
	query += 'phone_no=' + escape(form.elements['phone13b'].value.replace(/^\s*|\s*$/g,'')+form.elements['phone23b'].value.replace(/^\s*|\s*$/g,'')+form.elements['phone34b'].value.replace(/^\s*|\s*$/g,''))+'&';
	query += 'email_id=' + escape(form.elements['emailb'].value.replace(/^\s*|\s*$/g,''))+'&';
	query += 'join_date=' + escape(form.elements['jdateyb'].value.replace(/^\s*|\s*$/g,'')+'-'+form.elements['jdatemb'].value.replace(/^\s*|\s*$/g,'')+'-'+form.elements['jdatedb'].value.replace(/^\s*|\s*$/g,''))+'&';
	query += 'user_ssn=' + escape(form.elements['ssn13b'].value.replace(/^\s*|\s*$/g,'')+form.elements['ssn23b'].value.replace(/^\s*|\s*$/g,'')+form.elements['ssn34b'].value.replace(/^\s*|\s*$/g,''))+'&';
	query += 'user_cc_no=' + escape(form.elements['cardnob'].value.replace(/^\s*|\s*$/g,''))+'&';
	query += 'user_cc_limit=' + escape(form.elements['cardlimitb'].value.replace(/^\s*|\s*$/g,''));
    return query;
}

function updatepage(str){
   // document.getElementById("result").innerHTML = str;
	alert('Response from the server ' + str);
}

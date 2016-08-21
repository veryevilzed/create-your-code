
Generate Roulette Split Arrays
------------------------------

```
{% set className = "TestClass" %}

class {{ className }} {
    
    public boolean isSplit(int[] val) {
        if (val.length != 2) return false;
{% for i in range(1, 36) %}
{% if i % 3 != 0 %}
        if (val[0] == {{ i }} && val[1] == {{ i+1 }}) return true;
{% endif %}
{% endfor %}
        
{% for i in range(1, 33) %}
        if (val[0] == {{ i }} && val[1] == {{ i+3 }}) return true;
{% endfor %}

        return false;
    }
    
    public boolean isStreet(int[] val) {
        if (val.length != 3) return false;
{% for i in range(1, 36, 3) %}
        if (val[0] == {{ i }} && val[1] == {{ i+1 }} && val[1] == {{ i+2 }}) return true;
{% endfor %}
    return false;
    }
}

{{ codecomment() }}
```
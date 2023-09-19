is_root() {
    if [[ $(id -u) == 0 ]]; then
        return 0 # true
    else
        return 1 # false
    fi
}

conf_nginx() {
    cp /etc/nginx/sites-available/default /etc/nginx/sites-available/default.bak
    cp ./conf/nginx/default /etc/nginx/sites-available/
}

conf_apache2() {
    a2enmod proxy
    a2enmod proxy_http
    cp /etc/apache2/sites-available/000-default.conf /etc/apache2/sites-available/000-default.conf.bak
    cp ./conf/apache2/000-default.conf /etc/apache2/sites-available/
}

usage() {
    echo "Usage    : sudo bash configure.sh [option]"
    echo ""
    echo "[option] : nginx          - Install nginx configuration file"
    echo "         : apache2        - Install apache2 configuration file"
}

option=$1

if [[ $option == *"nginx"* ]] && is_root; then
    conf_nginx
elif [[ $option == *"apache2"* ]] && is_root; then
    conf_apache2
elif is_root; then
    printf "Option not recognized!\n\n"
    usage
else
    printf "!!! Run this script as root !!!\n"
    printf "===============================\n\n"
    usage   
fi

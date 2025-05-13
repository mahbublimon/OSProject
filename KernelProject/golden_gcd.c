#include <linux/init.h>
#include <linux/kernel.h>
#include <linux/module.h>
#include <linux/hash.h>
#include <linux/gcd.h>

/* Declare functions to silence warnings */
int simple_init(void);
void simple_exit(void);

int simple_init(void) {
    printk(KERN_INFO "Loading Module: GOLDEN_RATIO_PRIME = %llu\n", GOLDEN_RATIO_PRIME);
    return 0;
}

void simple_exit(void) {
    printk(KERN_INFO "Removing Module: GCD(3700, 24) = %lu\n", gcd(3700, 24));
}

module_init(simple_init);
module_exit(simple_exit);

MODULE_LICENSE("GPL");
MODULE_DESCRIPTION("Prints kernel constants");
MODULE_AUTHOR("Kazi Mahbub Morshed Limon");